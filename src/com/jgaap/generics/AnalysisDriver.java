/**
 *   JGAAP -- Java Graphical Authorship Attribution Program
 *   Copyright (C) 2009 Patrick Juola
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation under version 3 of the License.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/
package com.jgaap.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for statistical analysis methods. As an abstract class, can only be
 * instantiated through subclasses. Legacy code inherited from WAY back.
 * 
 * @author unknown
 * @since 1.0
 */
public abstract class AnalysisDriver extends Parameterizable implements Comparable<AnalysisDriver>, Displayable {

	public abstract String displayName();

	public abstract String tooltipText();

	public abstract boolean showInGUI();


    /**
     * Generic statistical analysis method (abstract). Analyze a given unknown
     * EventSet in terms of its similarity (broadly defined) to elements of a
     * Vector of EventSets of known authorship. Legacy code from WAY back. We
     * should probably add a verify() method as well once the technology
     * improves.
     * 
     * @param unknown
     *            the EventSet to be analyzed
     * @param known
     *            a vector of EventSets of known authorship
     * @return a String representing the name of the author assigned
     */
    abstract public String analyze(EventSet unknown, List<EventSet> known);

    public void analyze(Document unknown, List<Document> known){
    	for(EventDriver eventDriver : unknown.getEventSets().keySet()){
    		List<EventSet> knownEventSet = new ArrayList<EventSet>();
    		for(Document document : known){
    			knownEventSet.add(document.getEventSet(eventDriver));
    		}
    		unknown.addResult(this, eventDriver, analyze(unknown.getEventSet(eventDriver), knownEventSet));
    	}
    }
    
    public int compareTo(AnalysisDriver o){
    	return displayName().compareTo(o.displayName());
    }
    
}
