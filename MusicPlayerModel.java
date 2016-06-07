package cs3500.music.model;

import java.util.List;

/**
 * Interface to represent functionality provided by a music player
 */
public interface MusicPlayerModel {
  /**
   * Adds the given note to the piece
   *
   * @param n The note to be added to the piece
   */
  void read(Note n);

  /**
   * Adds the given notes to the piece
   *
   * @param n The notes to be added to the piece, in a List
   */
  void read(List<Note> n);

  /**
   * Gets the notes at the given beat of the piece. If the beat is not in the piece (less than 0 or
   * greater than the length of the piece), throws an IllegalArgumentException
   *
   * @param beat The beat of the piece to get the notes from
   * @return A List of the notes at the given beat
   * @throws IllegalArgumentException if the beat is not within the piece
   */
  List<Note> getNotes(int beat);

  /**
   * Returns a string that gives the current piece when displayed
   *
   * Should be in the following format; a hyphen represents a space, Nn represents a note and its
   * octave, X begins a note, | sustains that note: ----Nn---Nn---Nn---Nn---Nn---Nn-
   * -0--X----X--------------X------- -1--|---------X---------|-------
   * -2--|----X----|----X------------ -3--X----|----|----|----X------- ...
   * YY--|----|----X--------------X--
   *
   * @returns A string that represents the current piece
   */
  String getSong();

}
