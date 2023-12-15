using LiftAndShiftMVC.Models;
using Microsoft.EntityFrameworkCore;

namespace LiftAndShiftMVC.Data
{
    public class AzureDbContext : DbContext
    {
        public AzureDbContext(DbContextOptions<AzureDbContext> options) : base(options) { }

        public DbSet<Person>? Person { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Person>().HasKey(x => x.BusinessEntityID);
            base.OnModelCreating(modelBuilder);
        }
    }
}