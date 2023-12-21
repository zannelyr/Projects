using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using LiftAndShiftMVC.Data;
using LiftAndShiftMVC.Models;
using LiftAndShiftMVC.ViewModel;

namespace LiftAndShiftMVC.Controllers
{
    public class PersonController : Controller
    {
        private readonly LocalDbContext _localDbContext;
        private readonly AzureDbContext _azureDbContext;

        public PersonController(LocalDbContext localDbContext, AzureDbContext azureDbContext)
        {
            _localDbContext = localDbContext;
            _azureDbContext = azureDbContext;
        }
        // GET: Person
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Index(string selectedDbContext, int recordCount)
        {
            IQueryable<Person> query;

            if (selectedDbContext == "Local")
            {
                query = _localDbContext.Person;
            }
            else
            {
                query = _azureDbContext.Person;
            }

            if (recordCount > 0 && recordCount < await query.CountAsync())
            {
                query = query.Take(recordCount);
            }

            var persons = await query.ToListAsync();

            var viewModel = new PersonViewModel
            {
                Persons = persons,
                RecordCount = recordCount
            };

            return View(viewModel);
        }
    }
}
