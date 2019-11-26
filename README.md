# Foodstuffs

An API solution that provides lunch options from a set of recipes based on ingredients available.



## Running locally
```
$ git clone <project> 
$ cd <project> 

$ ./gradlew run
```

## Running tests

```
$ ./gradlew test
```

## Usage

Access via browser or any API Client (e.g. [Postman](https://www.getpostman.com/) or [Insomnia](https://insomnia.rest/))

``` 
http://localhost:8080/lunch
```

> Important note: <br>
> 
> There is an optional parameter called `date`. <br>
> If this parameter hasn't explicitly used, it will be considered the date as today. <br>
> Otherwise, the date considered will be that used via parameter.

With parameter `date` (YYYY-MM-d):

```
http://localhost:8080/lunch?date=2019-10-10 
```

## Assumptions

- The recipes is ordered by the number of ingredients between `best-before` and `use-by`.

- All the recipes that contains any ingredient expired (based on `use-by`) will be ignored.  

