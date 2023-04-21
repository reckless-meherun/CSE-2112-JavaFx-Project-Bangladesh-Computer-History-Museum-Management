package application.museum.Departments;

public class Software extends Curatorial_dept
{
    private String Documents;

    public Software()
    {

    }
    public void setAdditionDocuments (String doc)
    {
        this.Documents = doc;
    }
    public String getAdditionalDocuments ()
    {
        return this.Documents;
    }
}
