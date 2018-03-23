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

import pn.models.GilberthTriangleModel;
import pn.views.GilberthTriangleView;

/**
 * This class implements a Gilberth Triangle Controller as a part of Runnable
 * interface that operate on Gilberth Triangle View and Gilberth Triangle Model.
 * <p>
 *
 *
 * @author Eyal Segev
 * @author Itay Ben Shushan
 * @see GilberthTriangleView
 * @see GilberthTriangleModel
 * @see Runnable
 * @version %I%, %G%
 * @since 1.0
 */
public class GilberthTriangleController implements Runnable {

    private int size;
    private GilberthTriangleView view;

    /**
     * Default Constractor set size to 1.
     */
    public GilberthTriangleController() {
        this.size = 1;
    }

    /**
     * Constractior that accept Gilberth Triangle size.
     *
     * @param size required dimmention of the triangle.
     */
    public GilberthTriangleController(int size) {
        this.size = size;
    }

    /**
     * Setter for Gilberth Triangle size data member.
     *
     * @param size required dimmention of the triangle.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Builds Gilberth Triangle View and connect between view and model.
     */
    @Override
    public void run() {
        view = new GilberthTriangleView();
        GilberthTriangleModel model = new GilberthTriangleModel(view.getjPanel1(), size);
        model.buildGilberthTriangleGrid();
        view.setVisible(true);
    }

    /**
     * Releases resources allocated for a context.
     */
    public void stop() {
        view.dispose();
    }

}
