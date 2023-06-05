# seru_app_alfian_alamsyah

## Gambaran besar API

- API telah di deploy ke server dengan domain https://seruappalfianalamsyah-production.up.railway.app
  juga sudah dimasukan data dummy untuk mencoba API

- Untuk melihat nama field yang digunakan di API (seperti nama filed di body atau request parameter)
  bisa dilihat pada contoh yang akan ditunjukan di bawah. Secara garis besar, untuk field di body
  menggunakan snake case (misalnya branch_id) sedangkan untuk di query param menggunakan tanda "-"
  untuk memisahkan kata (misal "year-id=1&model-id=1")

- untuk mengakses API, terlebih dahulu dilakukan register. setelah itu di lakukan authentication
  untuk mendapatkan JWT (BEARER TOKEN AUTHORIZATION). Token yang di dapatkan dapat digunakan untuk mengakses API. untuk
  register dan authentication tidak perlu menggunakan JWT. POST,DELETE,PUT hanya bisa
  digukanakan oleh admin, sedangkan non admin hanya bisa untuk melakukan GET (readonly).
  Terkecuali untuk read data table user, karena menurut saya data table user adalah data sensitive
  jadi tetap hanya admin yang bisa melakukan GET data

- ada fitur untuk update user password. dengan syarat old_password harus sama dengan password
  saat ini yang nantinya akan digantikan dengan new_password. apabila salah akan throw exeption.
  kedua filed old_password dan new_password harus diisi untuk mengganti password.

- updaetUser https://seruappalfianalamsyah-production.up.railway.app/users/3<br>
  body {
  "old_password":"123",
  "new_password":"234"
  }

- untuk menggunakan pagination, ada dua request param yang harus diberikan yaitu "limit" dan "page"
  kedua request param tersebut harus diberikan, apabila tidak di berikan atau hanya salah satu
  yang diberikan maka pagination tidak akan berfungsi, dan API secata default akan melakukan findALL biasa
  contoh API dengan pagination <br>
  "https://seruappalfianalamsyah-production.up.railway.app/users?limit=2&page=2"

- untuk mengupdate data dengan PUT METHODE, tidak harus semua filed diisi, bisi hanya beberapa filed atau semua filed untuk bisa mengupdate DATA
  contoh, mengganti data vehicle models<br>
  PUT https://seruappalfianalamsyah-production.up.railway.app/price-list/1<br>
  body {
  "price":1,
  "model_id":1,
  "year_id":1
  }<br>
  bisa juga filed yang diisi tidak lengkap. Jadi bebas untuk apa dan berapa data field yang diganti<br>
  body {
  "year_id":1
  }

- Implementasi filter tiap entity (API) dengan column yang tersedia

Contoh: Kita punya data Vehicle Brand (vehicle_brands) dengan ID = 1. Maka kita bisa melakukan filter dengan kolom brand_id seperti di bawah ini.
Endpoint: /vehicle-types?brand_id=1

- Ada filed unique di table

Pada table user field yang unik adalah field username, sedangkan pada table lain filed
yang unik misalnya field name. Tidak bisa memasukan value yang sama pada filed unique ini. Apabila dilakukan akan throw exeption

## Register and Authentication API

- register

https://seruappalfianalamsyah-production.up.railway.app/register<br>
body {
"username":"adik",  
 "password":"123",
"is_admin":true
}

- authentication

https://seruappalfianalamsyah-production.up.railway.app/authenticate<br>
body {
"username":"adik",
"password":"123"
}

## USER API

- getUser
  https://seruappalfianalamsyah-production.up.railway.app/users/2

- getAllUser
  https://seruappalfianalamsyah-production.up.railway.app/users?limit=2&page=2

- updaetUser

https://seruappalfianalamsyah-production.up.railway.app/users/3<br>
body {
"username":"jaya",
"is_admin":true,
"old_password":"123",
"new_password":"234"
}<br>
ada fitur untuk update user password. dengan syarat old_password harus sama dengan password
saat ini yang nantinya akan digantikan dengan new_password. apabila salah akan throw exeption.
kedua filed old_password dan new_password harus diisi untuk mengganti password.

- deleteUser

https://seruappalfianalamsyah-production.up.railway.app/users/7

## Vehicle Years API

- addVehicleYears

https://seruappalfianalamsyah-production.up.railway.app/vehicle-years<br>
body { "year":"2009"}

- getVEhicleYears

https://seruappalfianalamsyah-production.up.railway.app/vehicle-years/1

- getAllVehicleYears

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-years?limit=2&page=2

## updateVehicleYears

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-years/1

- body {
  "year":"2009"
  }

## delete vehicle years

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-years/10

# Vehicle Brands API

## addVehicleBrands

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-brands

- body {
  "name":"red eye"
  }

## getVehicleBrands

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-brands/1

## getAllVehicleBrands

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-brands?page=2&limit=2

## updateVehicleBrands

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-brands/8

- body {
  "name":"red eye"
  }

## deleteVehicleBrands

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-brands/10

# Vehicle Types API

## addVehicleTypes

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-types
- body {
  "name":"honda 20",
  "brand_id":2
  }

## getVehicleTypes

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-types/1

## getAllVehicleTypes

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-types?limit=2&page=2&brand-id=1

## updateVehicleTypes

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-types/11

- body {
  "name":"honda baru",
  "brand_id":1
  }

## deleteVehicleTypes

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-types/10

# Vehicle Models API

## addVehicleModels

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-models

- body {
  "name":"honda 1 model aaa",
  "type_id":1
  }

## getVehicleModels

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-models/1

## getAllVehicleModels

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-models?page=2&limit=2&type-id=1

## updateVehicleModels

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-models/1

- body {
  "name":"honda 1 model baru",
  "type_id":1
  }

## deleteVehicleModels

- https://seruappalfianalamsyah-production.up.railway.app/vehicle-models/11

# Price Lists API

## addPricelist

- https://seruappalfianalamsyah-production.up.railway.app/price-list

- body {
  "price":746492,
  "model_id":1,
  "year_id":1
  }

## getPricelist

- https://seruappalfianalamsyah-production.up.railway.app/price-list/1

## getAllPricelist

- https://seruappalfianalamsyah-production.up.railway.app/price-list?limit=2&page=2&year-id=1&model-id=1

## updatePricelist

- https://seruappalfianalamsyah-production.up.railway.app/price-list/1

- body {
  "price":99999,
  "model_id":1,
  "year_id":2
  }

## deletePricelist

- https://seruappalfianalamsyah-production.up.railway.app/price-list/8
