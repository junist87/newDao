package voHandler;

import com.ciaosgarage.newDao.voHandler.VoHandler;
import com.ciaosgarage.newDao.voHandler.VoHandlerImpl;
import org.junit.Before;

public class VoHandlerTest {
    VoHandler voHandler;

    @Before
    public void setUp() {
        voHandler = new VoHandlerImpl();
    }
}
