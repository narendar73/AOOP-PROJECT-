interface CompressionStrategy {
    void compress(String fileName);
}
class JpegCompressionStrategy implements CompressionStrategy {
    public void compress(String fileName) {
        System.out.println("Compressing " + fileName + " using JPEG compression.");
    }
}
class PngCompressionStrategy implements CompressionStrategy {
    public void compress(String fileName) {
        System.out.println("Compressing " + fileName + " using PNG compression.");
    }
}
class GifCompressionStrategy implements CompressionStrategy {
    public void compress(String fileName) {
        System.out.println("Compressing " + fileName + " using GIF compression.");
    }
}
class ImageCompressor {
    private CompressionStrategy strategy;

    public void setStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compressImage(String fileName) {
        if (strategy != null) {
            strategy.compress(fileName);
        } else {
            throw new IllegalStateException("Compression strategy not set");
        }
    }
}

class ImageCompressorTest {
    public void testJpegCompression() {
        ImageCompressor compressor = new ImageCompressor();
        compressor.setStrategy(new JpegCompressionStrategy());
        assertDoesNotThrow(() -> compressor.compressImage("test.jpg"));
    }
    public void testPngCompression() {
        ImageCompressor compressor = new ImageCompressor();
        compressor.setStrategy(new PngCompressionStrategy());
        assertDoesNotThrow(() -> compressor.compressImage("test.png"));
    }
    public void testGifCompression() {
        ImageCompressor compressor = new ImageCompressor();
        compressor.setStrategy(new GifCompressionStrategy());
        assertDoesNotThrow(() -> compressor.compressImage("test.gif"));
    }
    public void testNoStrategySet() {
        ImageCompressor compressor = new ImageCompressor();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> compressor.compressImage("test.gif"));
        assertEquals("Compression strategy not set", exception.getMessage());
    }
}
class Expense {
    private String description;
    private double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
    }

    public void removeExpense(String description) {
        expenses.removeIf(expense -> expense.getDescription().equals(description));
    }

    public List<Expense> listExpenses() {
        return new ArrayList<>(expenses);
    }

    public double calculateTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}
class ExpenseTrackerTest {

    private ExpenseTracker expenseTracker;
    public void setUp() {
        expenseTracker = new ExpenseTracker();
    }
    public void testAddExpense() {
        expenseTracker.addExpense("Groceries", 50.0);
        List<Expense> expenses = expenseTracker.listExpenses();
        assertEquals(1, expenses.size());
        assertEquals("Groceries", expenses.get(0).getDescription());
        assertEquals(50.0, expenses.get(0).getAmount());
    }
    public void testRemoveExpense() {
        expenseTracker.addExpense("Groceries", 50.0);
        expenseTracker.addExpense("Rent", 1000.0);
        expenseTracker.removeExpense("Groceries");
        List<Expense> expenses = expenseTracker.listExpenses();
        assertEquals(1, expenses.size());
        assertEquals("Rent", expenses.get(0).getDescription());
    }
    public void testListExpenses() {
        expenseTracker.addExpense("Groceries", 50.0);
        expenseTracker.addExpense("Rent", 1000.0);
        List<Expense> expenses = expenseTracker.listExpenses();
        assertEquals(2, expenses.size());
    }
    public void testCalculateTotalExpenses() {
        expenseTracker.addExpense("Groceries", 50.0);
        expenseTracker.addExpense("Rent", 1000.0);
        double total = expenseTracker.calculateTotalExpenses();
        assertEquals(1050.0, total);
    }
}
