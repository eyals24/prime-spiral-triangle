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

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static pn.models.Utils.*;

/**
 * This class implements a Ulam Spiral Model that creates Ulam Spiral and pass
 * it visual and color represantation on the UlamSpiralView.
 * <p>
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @see pn.views.UlamSpiralView
 * @version %I%, %G%
 * @since 1.0
 */
public class UlamSpiralModel {

    private final JPanel panel;
    private final int size;
    private final int[] spiral;
    private int[] maxDiagonal;
    private final boolean[] primes;
    private final boolean[] isPrime;
    private final List<int[]> borders;
    private static final int[] TOP = {3, 0, 0, 0};
    private static final int[] LEFT = {0, 3, 0, 0};
    private static final int[] BOTTOM = {0, 0, 3, 0};
    private static final int[] RIGHT = {0, 0, 0, 3};

    /**
     * This Constructor method accept required JPanel element of the
     * UlamSpiralView and positive dimmention of Ulam Spiral.
     *
     * <p>
     * The method throw a <tt>NullPointerException</tt>
     * if the JPanel object provided to are null.
     *
     *
     * @param panel JPanel object to build Ulam Spiral
     * @param size dimmention size of the spiral
     *
     * @see pn.views.UlamSpiralView
     */
    public UlamSpiralModel(JPanel panel, int size) {
        this.panel = panel;
        if (size > 0) {
            this.size = size;
        } else {
            this.size = 1;
        }
        panel.setLayout(new GridLayout(this.size, this.size));
        spiral = new int[this.size * this.size];
        primes = new boolean[this.size * this.size];
        isPrime = isPrime(this.size * this.size);
        borders = new ArrayList<>();
        for (int i = 0; i < this.size * this.size; i++) {
            borders.add(null);
        }
        buildUlamSpiral();
        findMaxDiagonal();
    }

    /**
     * Perform visual presentation of Ulam Spiral array and first longest
     * diagonal on the UlamSpiralView.
     *
     * @see pn.views.UlamSpiralView
     */
    public void buildUlamSpiralGrid() {

        int n = size * size;

        for (int i = 0; i < n; i++) {
            JTextField textField = new JTextField();
            textField.setEditable(false);
            textField.setText(spiral[i] + "");
            textField.setHorizontalAlignment(JTextField.CENTER);
            if (primes[i]) {
                textField.setBackground(Color.BLACK);
                textField.setForeground(Color.WHITE);
            }
            Border border
                    = BorderFactory
                            .createMatteBorder(
                                    borders.get(i)[0],
                                    borders.get(i)[1],
                                    borders.get(i)[2],
                                    borders.get(i)[3], Color.MAGENTA);
            textField.setBorder(border);
            panel.add(textField);
        }

        resizeByPanel(panel);
    }

    /**
     * Getter of Max Diagonal array.
     * <p>
     * @return int[] that 0 index is the first value of the max diagonal 1 index
     * is the last value of the max diagonal 2 index is the number of prime
     * elements in the max diagoanl.
     */
    public int[] getMaxDiagonal() {
        return maxDiagonal;
    }

    /**
     * Builds Ulam Spiral as array that declared as data members of this
     * UlamSpiralModel class and perform required operation to find the first
     * longest diagonal.
     *
     */
    private void buildUlamSpiral() {

        int value = size * size;
        int minCol = 0;
        int maxCol = size - 1;
        int minRow = 0;
        int maxRow = (size - 1) * size;

        Queue<int[]> que = Stream.of(TOP, RIGHT, BOTTOM, LEFT)
                .collect(Collectors
                        .toCollection(LinkedList::new));

        int[] currBorder;

        while (value >= 1) {

            currBorder = (int[]) que.poll();
            que.add(currBorder);

            for (int i = minCol; i <= maxCol; i++) {
                primes[minRow + i] = isPrime[value];
                spiral[minRow + i] = value;
                if (i == maxCol) {
                    if (value > 1) {
                        currBorder = new int[]{3, 0, 0, 3};
                    } else {
                        currBorder = new int[]{3, 0, 3, 3};
                    }
                }
                if (borders.get(minRow + i) == null) {
                    borders.set(minRow + i, currBorder);
                }
                value--;
            }

            currBorder = (int[]) que.poll();
            que.add(currBorder);

            for (int i = minRow + size; i <= maxRow; i += size) {
                primes[i + maxCol] = isPrime[value];
                spiral[i + maxCol] = value--;
                if (i == maxRow) {
                    currBorder = new int[]{0, 0, 3, 3};
                }
                borders.set(i + maxCol, currBorder);
            }

            currBorder = (int[]) que.poll();
            que.add(currBorder);

            for (int i = maxCol - 1; i >= minCol; i--) {
                primes[maxRow + i] = isPrime[value];
                spiral[maxRow + i] = value;
                if (i == minCol) {
                    if (value > 1) {
                        currBorder = new int[]{0, 3, 3, 0};
                    } else {
                        currBorder = new int[]{3, 3, 3, 0};
                    }
                }
                borders.set(maxRow + i, currBorder);
                value--;
            }

            currBorder = (int[]) que.poll();
            que.add(currBorder);

            for (int i = maxRow - size; i >= minRow + size; i -= size) {
                primes[i + minCol] = isPrime[value];
                spiral[i + minCol] = value--;
                if (i == minRow + size) {
                    currBorder = new int[]{3, 3, 0, 0};
                }
                borders.set(i + minCol, currBorder);
            }

            minCol++;
            minRow = minRow + size;
            maxCol--;
            maxRow = maxRow - size;
        }

    }

    /**
     * Find max diagonal in the in the boolean array of the primes.
     */
    private void findMaxDiagonal() {

        // Assists class to perform calculation remember and recall required 
        // data.
        class PrimeChecker {

            int max, currentMax, from, currentFrom, to, currentTo;

            PrimeChecker() {
                max = currentMax = from = currentFrom = to = currentTo = 0;
            }

            void initCurrent() {
                if (currentMax > max) {
                    max = currentMax;
                    from = currentFrom;
                    to = currentTo;
                }
                currentMax = currentFrom = currentTo = 0;
            }

            void check(boolean isPrime, int spiralIndex) {
                if (isPrime) {
                    currentMax++;
                    if (currentFrom == 0) {
                        currentFrom = spiral[spiralIndex];
                    }
                    currentTo = spiral[spiralIndex];
                } else {
                    if (currentMax > max) {
                        max = currentMax;
                        from = currentFrom;
                        to = currentTo;
                    }
                    currentMax = currentFrom = currentTo = 0;
                }
            }
        }

        boolean flag = true;
        PrimeChecker primeChecker = new PrimeChecker();
        int n = size * size;

        // Check \ diagonal top triangle of the matrix
        for (int i = 0; i < size && flag; i++) {
            for (int j = 0; j < size - i && flag; j++) {
                primeChecker.check(primes[i + size * j + j], i + size * j + j);
                flag = primeChecker.max <= size - i;
            }
            primeChecker.initCurrent();
        }

        flag = true;
        primeChecker.initCurrent();

        // Check \ diagonal bottom triangle of the matrix
        for (int i = size, k = 0; i < n - size && flag; i += size, k++) {
            for (int j = 0; i + size * j + j < n && flag; j++) {
                primeChecker.check(primes[i + size * j + j], i + size * j + j);
                flag = primeChecker.max <= size - k;
            }
            primeChecker.initCurrent();
        }

        flag = true;
        primeChecker.initCurrent();

        // Check / diagonal top triangle of the matrix
        for (int i = 0, k = 0; i < n - size && flag; i += size, k++) {
            for (int j = 0; i - size * j + j >= 0 && flag; j++) {
                primeChecker.check(primes[i - size * j + j], i - size * j + j);
                flag = primeChecker.max <= size - k;
            }
            primeChecker.initCurrent();
        }

        flag = true;
        primeChecker.initCurrent();

        // Check / diagonal bottom triangle of the matrix
        for (int i = n - size + 1, k = 0; i < n && flag; i++, k++) {
            for (int j = 0; i - size * j + j >= size * 2 - 1 + size * j && flag; j++) {
                primeChecker.check(primes[i - size * j + j], i - size * j + j);
                flag = primeChecker.max <= size - k;
            }
            primeChecker.initCurrent();
        }

        maxDiagonal = new int[]{
            primeChecker.from, primeChecker.to, primeChecker.max
        };
    }

}
