/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.svg;

import com.alee.managers.icon.data.IconAdjustment;
import com.alee.utils.ColorUtils;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.xml.StyleAttribute;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.awt.*;
import java.util.List;

/**
 * Simple adjustments for making {@link SvgIcon} use grayscale fill and stroke colors.
 *
 * @author Mikle Garin
 */

@XStreamAlias ( "SvgGrayscale" )
public class SvgGrayscale implements IconAdjustment<SvgIcon>
{
    @Override
    public void apply ( final SvgIcon icon )
    {
        // Iterating through all strokes and fills
        final List<SVGElement> elements = icon.find ( "*[" + SvgElements.STROKE + "," + SvgElements.FILL + "]" );
        for ( final SVGElement element : elements )
        {
            // Modifying stroke and fill for elements where those exist
            makeGrayscale ( icon, element, SvgElements.STROKE );
            makeGrayscale ( icon, element, SvgElements.FILL );
        }
    }

    /**
     * Changes {@link SVGElement} attribute color value to grayscale.
     *
     * @param icon      {@link SvgIcon} to modify element for
     * @param element   {@link SVGElement} to modify attribute for
     * @param attribute {@link SVGElement} attribute
     */
    protected void makeGrayscale ( final SvgIcon icon, final SVGElement element, final String attribute )
    {
        if ( icon.hasAttribute ( element, attribute ) )
        {
            final StyleAttribute hexColor = icon.getAttribute ( element, attribute );
            final Color grayscale = ColorUtils.grayscale ( hexColor.getColorValue () );
            icon.setAttribute ( element, attribute, ColorUtils.getHexColor ( grayscale ) );
        }
    }
}