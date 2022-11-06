import net.minidev.json.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import pack.http.UrlCreator;
import pack.http.UrlRequest;

import java.io.IOException;
import java.time.Instant;

import static org.mockito.Mockito.when;

public class MocksTest {
  private String[] fields = {"response", "total_count"};

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  public void get(String hashTag, String response, int num, long start, long end) throws IOException, ParseException {
    when(
      UrlRequest.request(UrlCreator.createURL(
        hashTag,
        (start + ""),
        (end + "")
      ), fields)
    ).thenReturn(response);

    Assert.assertEquals(num, UrlRequest.request(UrlCreator.createURL(hashTag, (start + ""), (end + "")), fields));
  }

  @Test
  public void getCompany() throws IOException, ParseException {
    long end = Instant.now().getEpochSecond();
    long start = (end - 3600);
    get(
      "news",
      "{'response': {'total_count': 10}}",
      10,
      start,
      end
    );
  }
}
