package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LegacyXmlSystem oldSystem = new LegacyXmlSystem();
        JsonProcessor adapter = new XmlToJsonAdapter(oldSystem);
        adapter.postData("dummy_json");
    }
}