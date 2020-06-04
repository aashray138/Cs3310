package edu.wmich.cs3310.PA4.GSNTree;

import org.dom4j.Element;

public interface IArgumentElementFactory {
	// create instance of GSN node from its DOM4J element equivalent in Safety Pattern
		public ArgumentElement createArgumentElement(Element element);

}
