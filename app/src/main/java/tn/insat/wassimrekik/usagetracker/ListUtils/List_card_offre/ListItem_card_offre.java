package tn.insat.wassimrekik.usagetracker.ListUtils.List_card_offre;

/**
 * Created by wassimrekik on 27/03/2018.
 */

public class ListItem_card_offre {
    private String Title;
    private String Description;
    private int Image;

    public ListItem_card_offre(String n, String description, int image) {
        Title = n;
        Description = description;
        Image = image;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}