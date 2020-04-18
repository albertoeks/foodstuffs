# Foodstuffs

![](https://github.com/albertoeks/foodstuffs/workflows/Build/badge.svg)

An API solution that provides lunch options from a set of recipes based on ingredients available.



## Running locally

```
$ git clone <project> 
$ cd <project> 

$ ./gradlew run
```

## Running with Docker

```
docker run -p=8080:8080 docker.pkg.github.com/albertoeks/foodstuffs/docker-foodstuffs:latest
```

## Usage

Once everything has started up, you should be able to access the webapp via http://localhost:8080/lunch

> Important note: <br>
> 
> There is an optional parameter called `date`. <br>
> If this parameter hasn't explicitly used, it will be considered the date as today. <br>
> Otherwise, the date considered will be that used via parameter.

Without parameter

``` 
http://localhost:8080/lunch
```

With parameter `date` (YYYY-MM-d):

```
http://localhost:8080/lunch?date=2019-10-10 
```

## Running tests

```
$ ./gradlew clean test
```

## Assumptions

- The recipes is ordered by the number of ingredients between `best-before` and `use-by`.

- All the recipes that contains any ingredient expired (based on `use-by`) will be ignored.  

