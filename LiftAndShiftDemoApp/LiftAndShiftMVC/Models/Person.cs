using System.ComponentModel.DataAnnotations.Schema;

namespace LiftAndShiftMVC.Models
{
    [Table("Person", Schema = "Person")]
    public class Person
    {
        public int BusinessEntityID { get; set; }
        public string? Title { get; set; }
        public string? FirstName { get; set; }
        public string? MiddleName { get; set; }
        public string? LastName { get; set; }
    }
}
