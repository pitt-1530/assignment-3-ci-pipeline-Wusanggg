package edu.pitt.se;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistRecommenderTest {

    @Test
    public void classifyEnergy_returnsHigh_whenAverageBpmAtLeast140() {
        List<Integer> bpms = List.of(150, 150, 150);
        assertEquals("HIGH", PlaylistRecommender.classifyEnergy(bpms));
    }

    @Test
    public void classifyEnergy_returnsMedium_whenAverageBpmBetween100And139() {
        List<Integer> bpms = List.of(110, 120, 130);
        assertEquals("MEDIUM", PlaylistRecommender.classifyEnergy(bpms));
    }

    @Test
    public void classifyEnergy_returnsLow_whenAverageBpmBelow100() {
        List<Integer> bpms = List.of(80, 90, 100);
        assertEquals("LOW", PlaylistRecommender.classifyEnergy(bpms));
    }

    @Test
    public void classifyEnergy_boundaryValues100And140() {
        assertEquals("MEDIUM", PlaylistRecommender.classifyEnergy(List.of(100, 100)));
        assertEquals("HIGH", PlaylistRecommender.classifyEnergy(List.of(140, 140)));
    }

    @Test
    public void classifyEnergy_throwsException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> PlaylistRecommender.classifyEnergy(null));
        assertThrows(IllegalArgumentException.class,
                () -> PlaylistRecommender.classifyEnergy(List.of()));
    }

    @Test
    public void isValidTrackTitle_returnsTrue_forValidSimpleTitle() {
        assertTrue(PlaylistRecommender.isValidTrackTitle("Hello World"));
    }

    @Test
    public void isValidTrackTitle_rejectsNullOrEmptyOrTooLong() {
        assertFalse(PlaylistRecommender.isValidTrackTitle(null));
        assertFalse(PlaylistRecommender.isValidTrackTitle(""));
        assertFalse(PlaylistRecommender.isValidTrackTitle("ThisTitleIsDefinitelyMoreThan30Chars"));
    }

    @Test
    public void isValidTrackTitle_rejectsSpecialCharactersAndDigits() {
        assertFalse(PlaylistRecommender.isValidTrackTitle("Hello!"));
        assertFalse(PlaylistRecommender.isValidTrackTitle("Track 1"));
    }

    @Test
    public void isValidTrackTitle_rejectsOnlySpaces() {
        assertFalse(PlaylistRecommender.isValidTrackTitle("   "));
    }

    @Test
    public void normalizeVolume_returnsOriginalWithinRange() {
        assertEquals(50, PlaylistRecommender.normalizeVolume(50));
        assertEquals(0, PlaylistRecommender.normalizeVolume(0));
        assertEquals(100, PlaylistRecommender.normalizeVolume(100));
    }

    @Test
    public void normalizeVolume_clampsBelowZeroToZero() {
        assertEquals(0, PlaylistRecommender.normalizeVolume(-10));
    }

    @Test
    public void normalizeVolume_clampsAboveHundredToHundred() {
        assertEquals(100, PlaylistRecommender.normalizeVolume(120));
    }
}
