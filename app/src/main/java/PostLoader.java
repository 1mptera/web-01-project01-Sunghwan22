import models.Post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostLoader {

  public List<Post> loadpost() throws FileNotFoundException {
    List<Post> posts = new ArrayList<>();

    File file = new File("Posts.csv");

    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      Post post = parsePost(line);

      posts.add(post);
    }

    return posts;
  }

  public void postWriter(List<Post> posts) throws IOException {
    FileWriter fileWriter = new FileWriter("Posts.csv");
    for(Post post : posts) {
      String line = post.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  private Post parsePost(String line) {
    String[] words = line.split(",");
    String title = words[0];
    String content = words[1];
    String state = words[2];

    return new Post(title, content, state);
  }
}
