package eventdriven

import com.google.inject.AbstractModule
import com.google.inject.assistedinject.FactoryModuleBuilder
import eventdriven.action.*
import eventdriven.action.BlockingEventDelegate
import eventdriven.action.EventDrivenAction
import eventdriven.action.EventDrivenActionFactory
import eventdriven.game.AsyncGameExecutor
import eventdriven.game.EventDrivenGameExecutor

/**
 * Main Guice module for event driven games.
 */
class EventDrivenGameModule : AbstractModule() {
    override fun configure() {
        bind(EventDrivenGameExecutor::class.java).to(AsyncGameExecutor::class.java)
        bind(EventDelegate::class.java).to(BlockingEventDelegate::class.java)
        install(FactoryModuleBuilder()
            .implement(EventDrivenAction::class.java, EventDrivenActionImpl::class.java)
            .build(EventDrivenActionFactory::class.java))
    }
}