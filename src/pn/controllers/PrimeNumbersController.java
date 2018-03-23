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
package pn.controllers;

import java.awt.event.ActionEvent;
import pn.views.JDialogErrorView;
import pn.views.PrimeNumbersView;

/**
 * This class implements a Prime Numbers main Controller as a part of Runnable
 * interface that operate on Gilberth Triangle Controller, Ulam Spiral
 * Controller and Prime Numbers main view.
 * <p>
 *
 *
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @see pn.views.GilberthTriangleView
 * @see pn.models.GilberthTriangleModel
 * @see Runnable
 * @version %I%, %G%
 * @since 1.0
 */
public class PrimeNumbersController implements Runnable {

    private PrimeNumbersView view;
    private Integer dimmention;
    private UlamSpiralController ulamSpiralController;
    private GilberthTriangleController gilberthTriangleController;

    /**
     * Invoke Prime Numbers main Screen and adds action listeners to events on
     * the screen. Catchs event and pass text field value to validator method.
     *
     */
    @Override
    public void run() {

        view = new PrimeNumbersView();
        dimmention = 0;

        view.addjButton1ActionListener((ActionEvent ae) -> {

            if (isValidInput(view.getjTextField1Text())) {
                if (ulamSpiralController != null) {
                    ulamSpiralController.stop();
                }
                ulamSpiralController = new UlamSpiralController(dimmention);
                ulamSpiralController.setFrom(text -> view.setjTextField3Text(text));
                ulamSpiralController.setTo(text -> view.setjTextField4Text(text));
                ulamSpiralController.setMax(text -> view.setjTextField2Text(text));
                new Thread(ulamSpiralController).start();
            }

        });

        view.addjButton2ActionListener((ActionEvent ae) -> {

            if (isValidInput(view.getjTextField1Text())) {
                if (gilberthTriangleController != null) {
                    gilberthTriangleController.stop();
                }
                gilberthTriangleController
                        = new GilberthTriangleController(dimmention);
                new Thread(gilberthTriangleController).start();
            }

        });

        view.setVisible(true);
    }

    /**
     * Validates method to dimmention value.
     *
     * @param text required dimmention value passed from the main screen.
     * @return boolean error if validation is fault.
     */
    private boolean isValidInput(String text) {
        boolean error = false;

        try {
            dimmention = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            error = true;
        } finally {
            error = error || !(dimmention >= 1 && dimmention <= 100);
            if (error) {
                JDialogErrorView dialog = new JDialogErrorView(view, true);
                dialog.setErrorText(" Wrong input !");
                view.setjTextField1Text("");
                dialog.setVisible(true);
            }
        }

        return !error;

    }

}
