package org.apache.velocity.runtime.parser.node;

/*
 * Copyright 2000-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Writer;
import java.io.IOException;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.Parser;

/**
 * This class is responsible for handling Escapes
 *  in VTL.
 * 
 * Please look at the Parser.jjt file which is
 * what controls the generation of this class.
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id$ 
 */
public class ASTEscape extends SimpleNode 
{
    public String val;
    private char[] ctext;

    public ASTEscape(int id)
    {
        super(id);
    }
    
    public ASTEscape(Parser p, int id) 
    {
        super(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(ParserVisitor visitor, Object data) 
    {
        return visitor.visit(this, data);
    }
    
    public Object init( InternalContextAdapter context, Object data) 
        throws Exception
    {
        ctext =  val.toCharArray();
        return data;
    }

    public boolean render( InternalContextAdapter context, Writer writer)
        throws IOException
    {
        if (context.getAllowRendering()) {
            writer.write(ctext);
        }
        return true;
    }
}
