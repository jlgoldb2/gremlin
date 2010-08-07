package com.tinkerpop.gremlin.compiler.statements;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.GremlinScriptEngine;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;

import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class WhileTest extends BaseTest {

    public void testWhile() {
        final GremlinScriptEngine engine = new GremlinScriptEngine();
        final GremlinScriptContext context = new GremlinScriptContext();

        this.stopWatch();
        List results = (List) engine.eval("$x := 10\n$counter := 0\nwhile $x > 0\n$x := $x - 1\n$counter := $counter + 1\nend\n$x\n$counter\n", context);
        printPerformance("while statement", 10, "iterations", this.stopWatch());
        //System.out.println(results);
        assertEquals(results.size(), 4);
        assertEquals(results.get(0), 10);
        assertEquals(results.get(1), 0);
        assertEquals(results.get(2), 0);
        assertEquals(results.get(3), 10);
    }

    public void testEmbeddedWhile() {
        final GremlinScriptEngine engine = new GremlinScriptEngine();
        final GremlinScriptContext context = new GremlinScriptContext();

        this.stopWatch();
        List results = (List) engine.eval("$x := 10\n$counter := 0\nwhile $x > 0\n$y := 10\n$x := $x - 1\nwhile $y > 0\n$y := $y - 1\n$counter := $counter + 1\nend\nend\n$counter\n", context);
        printPerformance("while statement", 10, "iterations", this.stopWatch());
        //System.out.println(results);
        assertEquals(results.size(), 3);
        assertEquals(results.get(0), 10);
        assertEquals(results.get(1), 0);
        assertEquals(results.get(2), 100);
    }
}
