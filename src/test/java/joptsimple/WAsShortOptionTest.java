/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package joptsimple;

import static java.util.Collections.*;
import static org.infinitest.toolkit.CollectionMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 * @version $Id: WAsShortOptionTest.java,v 1.15 2010/11/21 05:04:05 pholser Exp $
 */
public class WAsShortOptionTest extends AbstractOptionParserFixture {
    @Before
    public final void initializeParser() {
        parser.accepts( "W" );
    }

    @Test
    public void wIsLegal() {
        OptionSet options = parser.parse( "-W", "silent" );

        assertOptionDetected( options, "W" );
        assertEquals( emptyList(), options.valuesOf( "W" ) );
        assertEquals( singletonList( "silent" ), options.nonOptionArguments() );
    }

    @Test
    public void recognizeLongOptionsTrumpsShortOptionW() {
        parser.recognizeAlternativeLongOptions( true );

        try {
            parser.parse( "-W", "silent" );
            fail();
        }
        catch ( UnrecognizedOptionException expected ) {
            assertThat( expected.options(), hasSameContentsAs( singleton( "silent" ) ) );
        }
    }
}
