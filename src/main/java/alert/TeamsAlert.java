package alert;

import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TeamsAlert {
	public static void sendMessage(String text) {
		try {
			String url = "https://outlook.office.com/webhook/c9d25875-b7c6-4d75-a871-a13da3af705a@f5918faf-6854-42eb-b8a7-fd14ef3c4e39/IncomingWebhook/4d326d700327464bb1ae5e93a9684a71/f06ab233-a856-4b8f-a797-ed3a6fea2417";
			JSONObject object = new JSONObject();
			object.put("text", "[UPDATE]" + text);
			Unirest.post(url).header("Content-Type", "application/json").body(object).asString();
		} catch (UnirestException e) {
			System.out.println("Teams stopped:" + e.getMessage());
		}
	}
}
