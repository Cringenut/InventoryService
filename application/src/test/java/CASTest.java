import com.example.model.InventoryItem;
import com.example.port.out.DomainEventRepository;
import com.example.port.out.InventoryItemRepository;
import com.example.service.InventoryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryItemServiceTest {

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @Mock
    private DomainEventRepository domainEventRepository;

    private InventoryItemService service;

    @BeforeEach
    void setUp() {
        service = new InventoryItemService(inventoryItemRepository, domainEventRepository);
    }

    @Test
    void reserve_retriesOnVersionConflict_andEventuallySucceeds() {
        String sku = "SKU-1";
        int qty = 2;

        InventoryItem initial = new InventoryItem(sku, 10, 0, 0);

        when(inventoryItemRepository.findBySku(sku))
                .thenReturn(Optional.of(initial))   // attempt 1
                .thenReturn(Optional.of(initial));  // attempt 2

        when(inventoryItemRepository.save(any(), anyLong()))
                .thenReturn(false) // conflict
                .thenReturn(true); // success

        doNothing().when(domainEventRepository).save(any());

        service.reserve(sku, qty);

        verify(inventoryItemRepository, times(2))
                .save(any(InventoryItem.class), anyLong());
        verify(domainEventRepository, times(1))
                .save(any());
    }

    @Test
    void reserve_failsAfterMaxRetries() {
        String sku = "SKU-1";
        int qty = 2;

        InventoryItem initial = new InventoryItem(sku, 10, 0, 0);

        when(inventoryItemRepository.findBySku(sku))
                .thenReturn(Optional.of(initial));

        when(inventoryItemRepository.save(any(), anyLong()))
                .thenReturn(false, false, false); // all attempts fail

        assertThrows(RuntimeException.class, () -> service.reserve(sku, qty));

        verify(inventoryItemRepository, times(3))
                .save(any(InventoryItem.class), anyLong());
    }
}
