package iuh.fit;

class TextField implements UIComponent {
    private String placeholder;
    public TextField(String placeholder) { this.placeholder = placeholder; }

    @Override
    public void draw(String indent) {
        System.out.println(indent + "[TextField]: " + placeholder);
    }
}