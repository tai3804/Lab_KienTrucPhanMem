package iuh.fit;

import java.util.ArrayList;
import java.util.List;

class UIContainer implements UIComponent {
    private String name;
    private List<UIComponent> children = new ArrayList<>();

    public UIContainer(String name) { this.name = name; }

    public void add(UIComponent component) {
        children.add(component);
    }

    public void remove(UIComponent component) {
        children.remove(component);
    }

    @Override
    public void draw(String indent) {
        System.out.println(indent + "+ Container: " + name);
        // Gọi đệ quy để vẽ tất cả các thành phần con bên trong
        for (UIComponent child : children) {
            child.draw(indent + "    ");
        }
    }
}