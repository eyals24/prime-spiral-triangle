/*
 * Copyright (C) 2017 Eyal Segev & Itay Ben Shushan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pn.models;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class consists exclusively of static methods. One of them make decision
 * if an entry number is prime and other perform resize on JFrame.
 *
 * <p>
 *
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @version %I%, %G%
 * @since 1.0*
 *
 */
public class Utils {

    /**
     * Implementation of Prime Sieve algorithm.
     *
     * @param num integer entry for calculation
     * @return boolean array that index represent number
     */
    public static boolean[] isPrime(int num) {

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[num + 1];
        for (int i = 2; i <= num; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= num using Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= num; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  num/factor
            if (isPrime[factor]) {
                for (int j = factor; factor * j <= num; j++) {
                    isPrime[factor * j] = false;
                }
            }
        }

        return isPrime;
    }

    /**
     * Retrives from JPanel it's JFrame container and resize it according to
     * entry JPanel size in additon of 100 px to height and with. If one of the
     * parameters of height and width is bigger when screen height or width this
     * method fit JFrame to full screen window size.
     * <p>
     * @param panel entry JPanel
     */
    public static void resizeByPanel(JPanel panel) {

        JFrame jframe = (JFrame) SwingUtilities.windowForComponent(panel);

        Dimension preferredSize = panel.getPreferredSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        preferredSize.height += 100;
        preferredSize.width += 100;

        if (preferredSize.height >= screenSize.height
                || preferredSize.width >= screenSize.width) {
            jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            jframe.setPreferredSize(preferredSize);
            jframe.pack();
        }
    }
}
