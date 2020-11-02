package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

        Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
        
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void pelaajatLoytyvatOikein() {
        assertEquals("Semenko", stats.search("Semenko").getName());
        assertNull(stats.search("ersnekko"));
    }
    
    @Test
    public void tiiminPelaajaListaTuleeOikein() {
        List<Player> pelaajat = stats.team("EDM");
        for (Player p : pelaajat) {
            assertTrue(p.getTeam().equals(("EDM")));
        }
        assertTrue(pelaajat.size() > 0);
    }
    
    @Test
    public void topScorersTuleeOikein() {
        List<Player> pelaajat = stats.topScorers(1);
        assertEquals("Gretzky", pelaajat.get(0).getName());
    }
}
