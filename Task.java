import java.time.LocalDate;

public class Task
{
    private String index;
    private String description;
    private boolean status;
    private LocalDate limitDate;
    
    public Task(String num, String description, boolean status, LocalDate limitDate)
    {
        this.index = num;
        this.description = description;
        this.status = status;
        this.limitDate = limitDate;
    }
    
    // Getter
    public String getNum() {
        return index;
    }

    // Setter
    public void setNum(String newName) {
        this.index = newName;
    }
    
    // Getter
    public String getDescription() {
        return description;
    }

    // Setter
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    
    // Getter
    public boolean getStatus() {
        return status;
    }

    // Setter
    public void setStatus(boolean newStatus) {
        this.status = newStatus;
    }
    
    // Getter
    public LocalDate getlimitDate() {
        return limitDate;
    }

    // Setter
    public void setlimitDate(LocalDate newlimitDate) {
        this.limitDate = newlimitDate;
    }
}