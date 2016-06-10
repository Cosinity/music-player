package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

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
    if (beat < 0) {
      throw new IllegalArgumentException("Invalid beat");
    }
    if (lowNote == null || n.compareTo(lowNote) < 0) {
      lowNote = n;
    }
    if (highNote == null || n.compareTo(highNote) > 0) {
      highNote = n;
    }
    while (beat + n.getDuration() > notes.size()) {
      notes.add(new ArrayList<Note>());
    }
    for (int i = beat; i < beat + n.getDuration(); i++) {
      if (i == beat) {
        notes.get(i).add(n);
      } else {
        // Use a note of length 0 to represent a continuation of a previous note
        notes.get(i).add(new Note(n.getName(), n.getOctave(), 0));
      }
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
    if (beat < 0) {
      throw new IllegalArgumentException("Invalid beat");
    }
    return notes.get(beat);
  }

  @Override
  public void remove(Note n, int beat) {
    if (!notes.get(beat).contains(n)) {
      throw new IllegalArgumentException("Note does not exist at this beat");
    }
    Note cont = new Note(n.getName(), n.getOctave(), 0);
    for (int i = beat; i < beat + n.getDuration(); i++) {
      notes.get(i).remove(n);
      notes.get(i).remove(cont);
    }
  }

  @Override
  public void change(Note n1, int beat, Note n2) {
    if (!notes.get(beat).contains(n1)) {
      throw new IllegalArgumentException("Note does not exist at this beat");
    }
    remove(n1, beat);
    write(n2, beat);
  }

  @Override
  public String getMusic() {
    if (notes.size() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    Note cur = lowNote;
    Note oneAboveHigh = highNote.nextNote();
    int beatDigits = String.valueOf(notes.size()).length();

    /*
     * Write the header
     */
    for (int i = 0; i < beatDigits; i++) {
      sb.append(" ");
    }
    // Use compareTo instead of equals because it doesn't check duration
    while (cur.compareTo(oneAboveHigh) != 0) {
      String str = String.format("%5s", cur.toString() + " ");
      sb.append(str);
      cur = cur.nextNote();
    }
    sb.append("\n");

    /*
     * Write the notes and beat numbers
     */
    cur = lowNote;
    for (int i = 0; i < notes.size(); i++) {
      sb.append(String.format("%"+beatDigits+"d", i));
      while (cur.compareTo(oneAboveHigh) != 0) {
        boolean contains = false;
        boolean beginning = false;
        for (Note n : notes.get(i)) {
          if (n.compareTo(cur) == 0) {
            contains = true;
            if (n.getDuration() != 0) {
              beginning = true;
            }
          }
        }
        if (contains) {
          if (beginning) {
            sb.append("  X  ");
          } else {
            sb.append("  |  ");
          }
        } else {
          sb.append("     ");
        }
        cur = cur.nextNote();
      }
      cur = lowNote;
      sb.append("\n");
    }

    return sb.toString();
  }
}
