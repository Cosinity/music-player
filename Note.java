package cs3500.music.model;

/**
 * Class to represent a note in a song. Has a field for the name of the note, a field for whether it
 * is sharp, a field for its octave, and a field for its duration in beats. Constructor throws an
 * illegal argument exception if the octave or duration is below 1. Provides accessor method for its
 * duration as well as overriding toString() to print the note as: "[letter]['#' if sharp][octave]".
 * Overrides equals(Object) and hashCode().
 */
public class Note {
  public enum Name {
    A("A"), ASHARP("A#"), B("B"), C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"), F("F"),
    FSHARP("F#"), G("G"), GSHARP("G#");

    private String val;

    private Name(String s) {
      this.val = s;
    }

    public String toString() {
      String out = val;
      return out;
    }
  }

  private final Name name;
  private final int octave;
  private final int duration;

  public Note(Name name, int octave, int duration) {
    this.name = name;
    if (octave < 1) {
      throw new IllegalArgumentException("Illegal octave");
    } else {
      this.octave = octave;
    }
    if (duration < 1) {
      throw new IllegalArgumentException("Illegal duration");
    } else {
      this.duration = duration;
    }
  }

  /**
   * Get the duration of this note
   *
   * @return an int equal to the duration of this note
   */
  public int getDuration() {
    int i = duration;
    return i;
  }

  @Override
  public String toString() {
    String result = name.toString();
    result += octave;
    return result;
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Note)) {
      return false;
    }
    Note other = (Note) that;
    return this.name.equals(other.name) && this.octave == other.octave
            && this.duration == other.duration;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(name, octave, duration);
  }
}
