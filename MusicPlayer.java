package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Implements the functionality of the MusicPlayerModel interface
 */
public class MusicPlayer implements MusicPlayerModel {
  private ArrayList<ArrayList<Note>> notes;
  private Note lowNote;
  private Note highNote;

  public MusicPlayer() {
    notes = new ArrayList<ArrayList<Note>>();
    lowNote = null;
    highNote = null;
  }

  @Override
  public void write(Note n, int beat) {
    if (beat < 1) {
      throw new IllegalArgumentException("Invalid beat");
    }
    if (lowNote == null || n.compareTo(lowNote) < 0) {
      lowNote = n;
    }
    if (highNote == null || n.compareTo(highNote) > ) {
      highNote = n;
    }
    while (beat + n.getDuration() > notes.size()) {
      notes.add(new ArrayList<Note>());
    }
    for (int i = beat - 1; i < beat - 1 + n.getDuration(); i++) {
      notes.get(i).add(n);
    }
  }

  @Override
  public void write(List<Note> n, List<Integer> beats) {
    if (n.size() != beats.size()) {
      throw new IllegalArgumentException("Incompatible lists");
    }
    for (int i = 0; i < n.size(); i++) {
      write(n.get(i), beats.get(i));
    }
  }

  @Override
  public List<Note> getNotes(int beat) {
    if (beat < 1) {
      throw new IllegalArgumentException("Invalid beat");
    }
    return notes.get(beat - 1);
  }

  @Override
  public void remove(Note n, int beat) {
    if (!notes.get(beat - 1).contains(n)) {
      throw new IllegalArgumentException("Note does not exist at this beat");
    }
    notes.get(beat - 1).remove(n);
  }

  @Override
  public void change(Note n1, int beat, Note n2) {
    if (!notes.get(beat - 1).contains(n1)) {
      throw new IllegalArgumentException("Note does not exist at this beat");
    }
    int nIdx = notes.get(beat - 1).indexOf(n1);
    notes.get(beat - 1).set(nIdx, n2);
  }

  @Override
  public String getNotation() {
    return null;
  }
}
