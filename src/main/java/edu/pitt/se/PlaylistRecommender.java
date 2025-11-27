package edu.pitt.se;

import java.util.List;

public class PlaylistRecommender {

    public static String classifyEnergy(List<Integer> bpms) {
        if (bpms == null || bpms.isEmpty()) {
            throw new IllegalArgumentException("bpms must not be null or empty");
        }

        int sum = 0;
        int count = 0;
        for (Integer bpm : bpms) {
            if (bpm == null) {
                throw new IllegalArgumentException("bpm value must not be null");
            }
            sum += bpm;
            count++;
        }

        double avg = sum / (double) count;

        if (avg >= 140) {
            return "HIGH";
        } else if (avg >= 100) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

    public static boolean isValidTrackTitle(String title) {
        if (title == null) {
            return false;
        }

        int length = title.length();
        if (length < 1 || length > 30) {
            return false;
        }

        if (!title.matches("^[A-Za-z ]+$")) {
            return false;
        }

        if (title.trim().isEmpty()) {
            return false;
        }

        return true;
    }

    public static int normalizeVolume(int volumeDb) {
        if (volumeDb < 0) {
            return 0;
        } else if (volumeDb > 100) {
            return 100;
        } else {
            return volumeDb;
        }
    }
}
