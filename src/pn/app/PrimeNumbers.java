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
package pn.app;

import java.awt.EventQueue;
import pn.controllers.PrimeNumbersController;

/**
 * <h1>Prime Numbers</h1>
 * The Prime Numbers program implements an application that simply generates
 * Ulam Spiral and Gilberth Triangle.
 * <p>
 *
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @version %I%, %G%
 * @since 1.0
 */
public class PrimeNumbers {

    /**
     * This is the main method which makes use to invoke PrimeNumbers main
     * screen.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new PrimeNumbersController());
    }

}
