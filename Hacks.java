public class Hacks {

  /**
  * Build a Van object and display its textual representation.
  *
  * Note that the Collection classes have
  * their own implementation of <code>toString</code>, as exemplified here
  * by the List field holding the Options.
  */
  
  public Hacks(
    String a,
    int b,
    int c,
    String d,
    Date e
  )
  {
    hTitleHack = a;
    hCategoryHack = b;
    hVotes = c;
    hAuthor = d;
    hCreatedAt = e;
  }

  //..other methods elided

  /**
  * Intended only for debugging.
  *
  * <P>Here, a generic implementation uses reflection to print
  * names and values of all fields <em>declared in this class</em>. Note that
  * superclass fields are left out of this implementation.
  *
  * <p>The format of the presentation could be standardized by using
  * a MessageFormat object with a standard pattern.
  */
  @Override public String toString() {
    StringBuilder result = new StringBuilder();
    String newLine = System.getProperty("line.separator");

    result.append(this.getClass().getName());
    result.append(" Contents {");
    //result.append(newLine);

    //determine fields declared in this class only (no fields of superclass)
    Field[] fields = this.getClass().getDeclaredFields();

    //print field names paired with their values
    for (Field field : fields) {
      result.append("  ");
      try {
        result.append(field.getName());
        result.append(": ");
        //requires access to private field:
        result.append(field.get(this));
      }
      catch (IllegalAccessException ex) {
        System.out.println(ex);
      }
      //result.append(newLine);
    }
    result.append("}");

    return result.toString();
  }

  // PRIVATE DATA
  private String hTitleHack;
  private int hCategoryHack;
  public int hVotes;
  private String hAuthor;
  private Date hCreatedAt;
}