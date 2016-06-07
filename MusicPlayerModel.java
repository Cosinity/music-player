package cs3500.music.model;

import java.util.List;

/**
 * Interface to represent functionality provided by a music player
 */
public interface MusicPlayerModel {
  /**
   * Adds the given note to the piece at the given beat
   *
   * @param n The note to be added to the piece
   * @param beat The beat to add the given note at
   * @throws IllegalArgumentException if the beat is less than 1
   */
  void read(Note n, int beat);

  /**
   * Adds the given notes to the piece at the given beats
   *
   * @param n The notes to be added to the piece, in a List
   * @param beats The beats to place the respective notes at
   * @throws IllegalArgumentException if there are not the same number of notes and beats or a beat
   *                                  is less than 1
   */
  void read(List<Note> n, List<Integer> beats);

  /**
   * Gets the notes at the given beat of the piece.
   *
   * @param beat The beat of the piece to get the notes from
   * @return A List of the notes at the given beat
   * @throws IllegalArgumentException if the beat is less than 1
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
