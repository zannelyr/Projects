using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalorieTrackerEF.Shared.Models
{
    class Book
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Author { get; set; }
        public int? Quantity { get; set; }
        public int Price { get; set; }
        public bool? Available { get; set; }
    }
}
