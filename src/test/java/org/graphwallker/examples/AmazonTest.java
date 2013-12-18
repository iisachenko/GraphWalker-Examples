package org.graphwallker.examples;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.graphwalker.conditions.EdgeCoverage;
import org.graphwalker.conditions.ReachedVertex;
import org.graphwalker.conditions.VertexCoverage;
import org.graphwalker.examples.modelAPI.Amazon;
import org.graphwalker.exceptions.StopConditionException;
import org.graphwalker.generators.A_StarPathGenerator;
import org.graphwalker.generators.CombinedPathGenerator;
import org.graphwalker.generators.RandomPathGenerator;
import org.graphwalker.multipleModels.ModelHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest {

  @Test
  public void a_star() throws InterruptedException, StopConditionException, URISyntaxException {
    ModelHandler modelhandler = new ModelHandler();

    // Get the model from resources
    URL url = MultiModelTest.class.getResource("/model/ShoppingCart.graphml");
    File file = new File(url.toURI());

    // Connect the model to a java class, and add it to graphwalker's modelhandler.
    // The model is to be executed using the following criteria:
    // EFSM: Extended finite state machine is set to true, which means we are using the data domain
    // in the model
    // Generator: a_star, we want to walk through the model using shortest possible path.
    // Stop condition: Edge coverage 100%, we want to walk every edge in the model.
    modelhandler.add("Amazon", new Amazon(file, true, new A_StarPathGenerator(new EdgeCoverage(1.0)), false));

    // Start executing the test
    modelhandler.execute("Amazon");

    // Verify that the execution is complete, fulfilling the criteria from above.
    Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

    // Print the statistics from graphwalker
    String actualResult = modelhandler.getStatistics();
    System.out.println(actualResult);
  }

  @Test
  public void random() throws InterruptedException, StopConditionException, URISyntaxException {
    ModelHandler modelhandler = new ModelHandler();

    // Get the model from resources
    URL url = MultiModelTest.class.getResource("/model/ShoppingCart.graphml");
    File file = new File(url.toURI());

    // Connect the model to a java class, and add it to graphwalker's modelhandler.
    // The model is to be executed using the following criteria:
    // EFSM: Extended finite state machine is set to true, which means we are using the data domain
    // in the model
    // Generator: random, walk through the model randomly
    // Stop condition: Edge coverage 100%, we want to walk every edge in the model.
    modelhandler.add("Amazon", new Amazon(file, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));

    // Start executing the test
    modelhandler.execute("Amazon");

    // Verify that the execution is complete, fulfilling the criteria from above.
    Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

    // Print the statistics from graphwalker
    String actualResult = modelhandler.getStatistics();
    System.out.println(actualResult);
  }

    @Test
    public void shoppingcart() throws InterruptedException, StopConditionException, URISyntaxException {
        ModelHandler modelhandler = new ModelHandler();

        // Get the model from resources
        URL url = MultiModelTest.class.getResource("/model/ShoppingCart.graphml");
        File file = new File(url.toURI());

        // Connect the model to a java class, and add it to graphwalker's modelhandler.
        // The model is to be executed using the following criteria:
        // Go the fastest path to the vertex v_ShoppingCart.
        modelhandler.add("Amazon", new Amazon(file, true, new A_StarPathGenerator(new ReachedVertex("v_ShoppingCart")), false));

        // Start executing the test
        modelhandler.execute("Amazon");

        // Verify that the execution is complete, fulfilling the criteria from above.
        Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

        // Print the statistics from graphwalker
        String actualResult = modelhandler.getStatistics();
        System.out.println(actualResult);
    }


    @Test
    public void multipleStopConditions() throws InterruptedException, StopConditionException, URISyntaxException {
        ModelHandler modelhandler = new ModelHandler();

        // Get the model from resources
        URL url = MultiModelTest.class.getResource("/model/ShoppingCart.graphml");
        File file = new File(url.toURI());

        /*  This setup does following:
         *    Execute a test that covers all vertices. After that, execute until
         *    we have reached the vertex with label:  v_ShoppingCart
         */
        CombinedPathGenerator generator = new CombinedPathGenerator();
        generator.addPathGenerator(new RandomPathGenerator(new VertexCoverage(1.0)));
        generator.addPathGenerator(new RandomPathGenerator(new ReachedVertex("v_ShoppingCart")));
        modelhandler.add("Amazon", new Amazon(file, true, generator, false));

        // Start executing the test
        modelhandler.execute("Amazon");

        // Verify that the execution is complete, fulfilling the criteria from above.
        Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

        // Print the statistics from graphwalker
        String actualResult = modelhandler.getStatistics();
        System.out.println(actualResult);
    }


}
