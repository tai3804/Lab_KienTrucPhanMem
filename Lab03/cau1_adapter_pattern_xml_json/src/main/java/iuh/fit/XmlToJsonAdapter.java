package iuh.fit;

class XmlToJsonAdapter implements JsonProcessor {
    private LegacyXmlSystem legacySystem;

    public XmlToJsonAdapter(LegacyXmlSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void postData(String json) {
        String xml = legacySystem.provideDataInXml();
        String convertedJson = convertXmlToJson(xml);

        System.out.println("--- Adapter đang xử lý ---");
        System.out.println("Dữ liệu gốc (XML): " + xml);
        System.out.println("Dữ liệu đã chuyển đổi (JSON): " + convertedJson);
        System.out.println("--- Gửi dữ liệu JSON đến Web Service thành công ---");
    }

    private String convertXmlToJson(String xml) {
        return xml.replace("<", "{").replace(">", "}")
                .replace("user", " ").trim();
    }
}