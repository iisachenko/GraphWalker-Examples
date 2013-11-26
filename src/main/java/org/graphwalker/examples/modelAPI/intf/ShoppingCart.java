package org.graphwalker.examples.modelAPI.intf;

/**
 * Auto-generated interface for model
 */
public interface ShoppingCart {



  /**
   * This method implements the Edge 'e_AddBookToCart'
   *
   *
   *
   */
  public void e_AddBookToCart() throws Exception;


  /**
   * This method implements the Edge 'e_ClickBook'
   *
   *
   *
   */
  public void e_ClickBook() throws Exception;


  /**
   * This method implements the Edge 'e_EnterBaseURL'
   *
   *
   *
   */
  public void e_EnterBaseURL() throws Exception;


  /**
   * This method implements the Edge 'e_SearchBook'
   *
   *
   *
   */
  public void e_SearchBook() throws Exception;


  /**
   * This method implements the Edge 'e_ShoppingCart'
   *
   *
   *
   */
  public void e_ShoppingCart() throws Exception;


  /**
   * This method implements the Edge 'e_StartBrowser'
   *
   *
   *
   */
  public void e_StartBrowser() throws Exception;


  /**
   * This method implements the Vertex 'v_BaseURL'
   * Verify that Amazon's home page is properly displayed.
   */
  public void v_BaseURL() throws Exception;


  /**
   * This method implements the Vertex 'v_BookInformation'
   * Verify that the page displaing detailed information regarding '
   * Practical Model-Based Testing: A Tools Approach' is correct.
   */
  public void v_BookInformation() throws Exception;


  /**
   * This method implements the Vertex 'v_BrowserStarted'
   * Verify that the web browser is upp running.
   */
  public void v_BrowserStarted() throws Exception;


  /**
   * This method implements the Vertex 'v_OtherBoughtBooks'
   * Verify that the page 'Shopping Cart' now displays information 'Customers Who Bought Practical Model-Based Testing: A Tools Approach Also Bought'
   */
  public void v_OtherBoughtBooks() throws Exception;


  /**
   * This method implements the Vertex 'v_SearchResult'
   * Verify that in the search result list, the book 'Practical Model-Based Testing: A Tools Approach by Mark Utting and Bruno Legeard' exists.
   */
  public void v_SearchResult() throws Exception;


  /**
   * This method implements the Vertex 'v_ShoppingCart'
   * Verify that the shopping cart page is displayed properly. The correct amount of added books should be: {$num_of_books}
   */
  public void v_ShoppingCart() throws Exception;


}

