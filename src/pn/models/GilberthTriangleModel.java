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
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static pn.models.Utils.*;

/**
 * This class implements a Gilberth Triangle Model that creates Gilberth
 * Triangle and pass it visual and color represantation on the
 * GilberthTriangleView.
 * <p>
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @see pn.views.GilberthTriangleView
 * @version %I%, %G%
 * @since 1.0
 */
public class GilberthTriangleModel {

    private final JPanel panel;
    private final int size;
    private final int[] triangle;
    private final boolean[] isPrime;

    /**
     * This Constructor method accept required JPanel element of the
     * GilberthTriangleView and positive dimmention of Gilberth Triangle.
     *
     * <p>
     * The method throw a <tt>NullPointerException</tt>
     * if the JPanel object provided to are null.
     *
     *
     * @param panel JPanel objects to build triangle
     * @param size dimmention size of the triangle
     *
     * @see pn.views.GilberthTriangleView
     */
    public GilberthTriangleModel(JPanel panel, int size) {
        this.panel = panel;
        if (size > 0) {
            this.size = size;
        } else {
            this.size = 1;
        }
        panel.setLayout(new GridLayout(this.size, 2 * this.size - 1));
        triangle = new int[size * size];
        isPrime = isPrime(size * size);
        Arrays.fill(triangle, -1);
        buildGilberthTriangle();
    }

    /**
     * Perform visual presentation of Gilberth Triangle array on the
     * pn.views.GilberthTriangleView.
     *
     * @see pn.views.GilberthTriangleView
     */
    public void buildGilberthTriangleGrid() {

        int n = size * (2 * size - 1);

        for (int i = 0, j = 0; i < n; i++) {
            JTextField textField = new JTextField();
            textField.setEditable(false);
            textField.setBorder(null);

            if (i % 2 == 0) {
                switch (triangle[j]) {
                    case -1:
                        break;
                    case 0:
                        textField.setBackground(Color.white);
                        break;
                    case 1:
                        textField.setBackground(Color.cyan);
                        break;
                    default:
                        textField.setBackground(Color.yellow);
                        break;
                }
                if (triangle[j] != -1) {
                    Border border = BorderFactory.createRaisedBevelBorder();
                    textField.setBorder(border);
                    textField.setText(triangle[j] + "");
                    textField.setHorizontalAlignment(JTextField.CENTER);
                }
                j++;
            }
            panel.add(textField);
        }

        resizeByPanel(panel);
    }

    /**
     * Builds Gilberth Triangle as array that declared as data members of this
     * GilberthTriangleModel class.
     *
     */
    private void buildGilberthTriangle() {

        triangle[0] = 2;

        for (int next = 3, i = 1; i < size; next += 2) {
            if (isPrime[next]) {
                triangle[i++] = next;
            }
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                triangle[i * size + j]
                        = Math.abs(triangle[(i - 1) * size + j]
                                - triangle[(i - 1) * size + j + 1]);
            }
        }
    }

    /**
     * Returns a String representation of Gilberth Triangle.
     *
     * @return String
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str = str + "[\t";
            for (int j = 0; j < size; j++) {
                str = str + triangle[i * size + j] + "\t";
            }
            str = str + "]\n";
        }

        return str;
    }

}
