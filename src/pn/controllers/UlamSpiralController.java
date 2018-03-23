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

import java.util.function.Consumer;
import pn.models.UlamSpiralModel;
import pn.views.UlamSpiralView;

/**
 * This class implements a Ulam Spiral Controller as a part of Runnable
 * interface that operate on Ulam Spiral View and Ulam Spiral Model.
 * <p>
 *
 * Accepts and pass Cunsumer to the Model to update fields in the main Prime
 * Numbers screen view.
 *
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @see pn.views.GilberthTriangleView
 * @see pn.models.GilberthTriangleModel
 * @see Runnable
 * @see Consumer
 * @version %I%, %G%
 * @since 1.0
 */
public class UlamSpiralController implements Runnable {

    private int size;
    private UlamSpiralView view;
    private Consumer<String> from, to, max;

    /**
     * Default Constractor set size to 1.
     */
    public UlamSpiralController() {
        this.size = 1;
    }

    /**
     * Constractior that accept Ulam Spiral size.
     *
     * @param size required dimmention of the spiral.
     */
    public UlamSpiralController(int size) {
        this.size = size;
    }

    /**
     * Setter for Ulam Spiral size data member.
     *
     * @param size required dimmention of the spiral.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Builds Ulam Spiral View and connect between view and model and main Prime
     * Numbers View text fields and Ulam Spiral model.
     *
     * @see PrimeNumbersController
     */
    @Override
    public void run() {
        view = new UlamSpiralView();
        UlamSpiralModel ulamSpiralModel = new UlamSpiralModel(view.getjPanel1(), size);
        ulamSpiralModel.buildUlamSpiralGrid();
        from.accept(ulamSpiralModel.getMaxDiagonal()[0] + "");
        to.accept(ulamSpiralModel.getMaxDiagonal()[1] + "");
        max.accept(ulamSpiralModel.getMaxDiagonal()[2] + "");
        view.setVisible(true);
    }

    /**
     * Releases resources allocated for a context.
     */
    public void stop() {
        view.dispose();
    }

    /**
     * Setter for Consumer of text field from.
     * <p>
     *
     * @param from output field of longest diagonal start value
     * @see Consumer
     */
    public void setFrom(Consumer<String> from) {
        this.from = from;
    }

    /**
     * Setter for Consumer of text field to.
     * <p>
     *
     * @param to output field of longest diagonal end value
     * @see Consumer
     */
    public void setTo(Consumer<String> to) {
        this.to = to;
    }

    /**
     * Setter for Consumer of text field max.
     * <p>
     *
     * @param max output field of longest diagonal max value
     * @see Consumer
     */
    public void setMax(Consumer<String> max) {
        this.max = max;
    }

}
