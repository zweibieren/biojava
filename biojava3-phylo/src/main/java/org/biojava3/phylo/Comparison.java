/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biojava3.phylo;

/**
 *
 * @author willishf
 */
public class Comparison {


  /**
   * this is a gapped PID calculation
   *
   * @param s1
   *                SequenceI
   * @param s2
   *                SequenceI
   * @return float
   */
  public final static float PID(String seq1, String seq2)
  {
    return PID(seq1, seq2, 0, seq1.length());
  }

  static final int caseShift = 'a' - 'A';

  // Another pid with region specification
  public final static float PID(String seq1, String seq2, int start, int end)
  {

    int s1len = seq1.length();
    int s2len = seq2.length();

    int len = Math.min(s1len, s2len);

    if (end < len)
    {
      len = end;
    }

    if (len < start)
    {
      start = len - 1; // we just use a single residue for the difference
    }

    int bad = 0;
    char chr1;
    char chr2;

    for (int i = start; i < len; i++)
    {
      chr1 = seq1.charAt(i);

      chr2 = seq2.charAt(i);

      if ('a' <= chr1 && chr1 <= 'z')
      {
        // TO UPPERCASE !!!
        // Faster than toUpperCase
        chr1 -= caseShift;
      }
      if ('a' <= chr2 && chr2 <= 'z')
      {
        // TO UPPERCASE !!!
        // Faster than toUpperCase
        chr2 -= caseShift;
      }

      if (chr1 != chr2 && !isGap(chr1) && !isGap(chr2))
      {
        bad++;
      }
    }

    return ((float) 100 * (len - bad)) / len;
  }

  /**
   * DOCUMENT ME!
   *
   * @param c
   *                DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public static final boolean isGap(char c)
  {
    return (c == '-' || c == '.' || c == ' ') ? true : false;
  }

}
