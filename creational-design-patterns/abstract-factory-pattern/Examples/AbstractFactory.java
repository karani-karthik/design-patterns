
// Product Interface
interface Button {
    void render();
}

interface TextBox {
    void render();
}

interface ScrollBar {
    void render();
}

// Concrete Products
class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Light Button");
    }
}

class LightTestBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Light TextBox");
    }
}

class LightScrollBar implements ScrollBar {
    @Override
    public void render() {
        System.out.println("Rendering Light ScrollBar");
    }
}

class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Dark Button");
    }
}

class DarkTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Dark TextBox");
    }
}

class DarkScrollBar implements ScrollBar {
    @Override
    public void render() {
        System.out.println("Rendering Dark ScrollBar");
    }
}

// Abstract Factory
interface UIComponenetFactory {
    Button createButton();

    TextBox createTextBox();

    ScrollBar createScrollBar();
}

// Create Concrete Factories
class LightThemeFactory implements UIComponenetFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public TextBox createTextBox() {
        return new LightTestBox();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new LightScrollBar();
    }
}

class DarkThemeFactory implements UIComponenetFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public TextBox createTextBox() {
        return new DarkTextBox();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new DarkScrollBar();
    }

}

// Client
class UIApplication {

    private final Button button;
    private final TextBox textBox;
    private final ScrollBar scrollBar;

    public UIApplication(UIComponenetFactory factory) {
        this.button = factory.createButton();
        this.textBox = factory.createTextBox();
        this.scrollBar = factory.createScrollBar();
    }

    public void renderUI() {
        button.render();
        textBox.render();
        scrollBar.render();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        UIComponenetFactory factory = new LightThemeFactory();
        UIApplication application = new UIApplication(factory);

        application.renderUI();

    }
}