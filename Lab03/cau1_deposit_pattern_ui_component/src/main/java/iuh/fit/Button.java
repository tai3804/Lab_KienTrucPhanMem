package iuh.fit;

class Button implements UIComponent {
    private String label;
    public Button(String label) { this.label = label; }

    @Override
    public void draw(String indent) {
        System.out.println(indent + "[Button]: " + label);
    }
}