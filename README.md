# spring-boot-quartz-examples

## Scheduler
调度器，所有的调度都是由它控制

## Trigger
触发器，决定什么时候来执行任务

## JobDetail&Job
JobDetail定义的是任务数据，而真正的执行逻辑是在Job中。使用JobDetail+Job而不是Job，这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。而JobDetail & Job 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
