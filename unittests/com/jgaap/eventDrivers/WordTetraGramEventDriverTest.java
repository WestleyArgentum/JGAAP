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
import com.jgaap.generics.EventDriver;
import com.jgaap.generics.EventGenerationException;
import com.jgaap.generics.EventSet;

/**
 * @author Chris
 *
 */
public class WordTetraGramEventDriverTest {

	/**
	 * Test method for {@link com.jgaap.eventDrivers.WordNGramEventDriver#createEventSet(com.jgaap.generics.Document)}.
	 * @throws EventGenerationException 
	 */
	@Test
	public void testCreateEventSetDocumentSet() throws EventGenerationException {
		Document doc = new Document();
		doc.readStringText("Mary had a little lamb, little lamb. Its fleece was white as snow.");
		EventDriver eventDriver = new WordNGramEventDriver();
		eventDriver.setParameter("N", 4);
		EventSet sampleEventSet = eventDriver.createEventSet(doc);
		EventSet expectedEventSet = new EventSet();
		Vector<Event> tmp = new Vector<Event>();
		tmp.add(new Event("(Mary)-(had)-(a)-(little)", null));
		tmp.add(new Event("(had)-(a)-(little)-(lamb,)", null));
		tmp.add(new Event("(a)-(little)-(lamb,)-(little)", null));
		tmp.add(new Event("(little)-(lamb,)-(little)-(lamb.)", null));
		tmp.add(new Event("(lamb,)-(little)-(lamb.)-(Its)", null));
		tmp.add(new Event("(little)-(lamb.)-(Its)-(fleece)", null));
		tmp.add(new Event("(lamb.)-(Its)-(fleece)-(was)", null));
		tmp.add(new Event("(Its)-(fleece)-(was)-(white)", null));
		tmp.add(new Event("(fleece)-(was)-(white)-(as)", null));
		tmp.add(new Event("(was)-(white)-(as)-(snow.)", null));
		expectedEventSet.addEvents(tmp);
		assertTrue(expectedEventSet.equals(sampleEventSet));
		
	}

}
