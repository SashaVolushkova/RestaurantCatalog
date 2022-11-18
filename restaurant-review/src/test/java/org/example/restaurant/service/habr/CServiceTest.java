package org.example.restaurant.service.habr;

import org.example.restaurant.service.habr.model.B;
import org.example.restaurant.service.habr.model.C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CServiceTest {
    AService mockA = mock(AService.class);
    BService mockB = mock(BService.class);
    private final CService service = new CService(mockA, mockB);

    @Test
    public void testBFalseOne() throws Exception {
        B b = new B(false, B.Enum.One);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNotNull(c);
        assertEquals(1, c.getNum());
    }

    @Test
    public void testBFalseTwo() throws Exception {
        B b = new B(false, B.Enum.Two);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNotNull(c);
        assertEquals(2, c.getNum());
    }

    @Test
    public void testBFalseNull() throws Exception {
        B b = new B(false, null);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNull(c);
    }

    @Test
    public void testBFalseDefault() throws Exception {
        B b = new B(false, B.Enum.Default);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNull(c);
    }

    @Test
    public void testCException() throws Exception {
        when(mockB.doSmth(any())).thenThrow(Exception.class);
        assertThrowsExactly(CException.class, service::doSmth);
    }

    @Test
    public void testBTrue() throws Exception {
        B b = new B(true, B.Enum.One);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNull(c);
    }
}
