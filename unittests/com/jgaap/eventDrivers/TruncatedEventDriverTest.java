/*
 * JGAAP -- a graphical program for stylometric authorship attribution
 * Copyright (C) 2009,2011 by Patrick Juola
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package com.jgaap.eventDrivers;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.jgaap.generics.Document;
import com.jgaap.generics.Event;
import com.jgaap.generics.EventGenerationException;
import com.jgaap.generics.EventSet;

/**
 * @author Patrick Juola
 *
 */
public class TruncatedEventDriverTest {

	/**
	 * Test method for {@link com.jgaap.eventDrivers.TruncatedEventDriver#createEventSet(com.jgaap.generics.Document)}.
	 * @throws EventGenerationException 
	 */
	@Test
	public void testCreateEventSetDocumentSet() throws EventGenerationException {

		/* test 1 -- straight up */
		Document doc = new Document();
		doc.readStringText(
"We hold these truths to be self-evident,\n"+
"\"My phone # is 867-5309; don't forget it!\" she said.\n"+
"\t\t\"I won't,\" \t he grumbled.\n"

		);

		TruncatedEventDriver n = new TruncatedEventDriver();
		n.setParameter("length","3");
		EventSet sampleEventSet = n.createEventSet(doc);
		EventSet expectedEventSet = new EventSet();
		Vector<Event> tmp = new Vector<Event>();

		tmp.add(new Event("We", null));
		tmp.add(new Event("hol", null));
		tmp.add(new Event("the", null));
		tmp.add(new Event("tru", null));
		tmp.add(new Event("to", null));
		tmp.add(new Event("be", null));
		tmp.add(new Event("sel", null));
		tmp.add(new Event("\"My", null));
		tmp.add(new Event("pho", null));
		tmp.add(new Event("#", null));
		tmp.add(new Event("is", null));
		tmp.add(new Event("867", null));
		tmp.add(new Event("don", null));
		tmp.add(new Event("for", null));
		tmp.add(new Event("it!", null));
		tmp.add(new Event("she", null));
		tmp.add(new Event("sai", null));
		tmp.add(new Event("\"I", null));
		tmp.add(new Event("won", null));
		tmp.add(new Event("he", null));
		tmp.add(new Event("gru", null));

		expectedEventSet.addEvents(tmp);

//System.out.println("Expected is " +expectedEventSet.events.toString());
//System.out.println("Actual is " +sampleEventSet.events.toString());
		assertTrue(expectedEventSet.equals(sampleEventSet));

		// need somethign to test whether ot not it handles Numerc
		// correctly.
	}

}
