package ee.asaarnak.library.pages.author;

import java.util.UUID;

import ee.asaarnak.library.entities.Author;

/**
 * Page for ../author/update
 * 
 * @author Allar Saarnak
 * 
 */
public class UpdateAuthor extends AbstractAuthor {
  private UUID authorId;

  UUID onPassivate() {
    return authorId;
  }

  void onActivate(UUID authorId) {
    this.authorId = authorId;
    setAuthor(getDao().find(Author.class, authorId));
  }

  Object onSuccess() {
    getDao().update(getAuthor());
    return Index.class;
  }

  @Override
  public String getTitleMessage() {
    return getMessages().get("title.update");
  }

  public String getSubmitButtonMessage() {
    return getMessageByKey("submit.save");
  }
}
