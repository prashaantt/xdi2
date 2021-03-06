package xdi2.server.factory.impl;

import xdi2.core.ContextNode;
import xdi2.core.xri3.impl.XRI3Segment;
import xdi2.messaging.exceptions.Xdi2MessagingException;
import xdi2.messaging.target.AbstractMessagingTarget;
import xdi2.messaging.target.MessagingTarget;
import xdi2.messaging.target.Prototype;
import xdi2.messaging.target.Prototype.PrototypingContext;
import xdi2.messaging.target.contributor.ContributorMap;
import xdi2.messaging.target.interceptor.InterceptorList;
import xdi2.server.exceptions.Xdi2ServerException;
import xdi2.server.factory.AbstractMessagingTargetFactory;
import xdi2.server.registry.EndpointRegistry;

public abstract class PrototypingMessagingTargetFactory extends AbstractMessagingTargetFactory {

	private MessagingTarget prototypeMessagingTarget;

	@SuppressWarnings("unchecked")
	public void mountMessagingTarget(EndpointRegistry endpointRegistry, String messagingTargetPath, XRI3Segment owner, ContextNode ownerRemoteRootContextNode, ContextNode ownerContextNode) throws Xdi2MessagingException, Xdi2ServerException {

		// create new messaging target

		if (! (this.getPrototypeMessagingTarget() instanceof Prototype<?>)) {

			throw new Xdi2MessagingException("Cannot use messaging target " + this.getPrototypeMessagingTarget().getClass().getSimpleName() + " as prototype.", null, null);
		}

		PrototypingContext prototypingContext = new PrototypingContext(owner, ownerRemoteRootContextNode, ownerContextNode);

		Prototype<? extends MessagingTarget> messagingTargetPrototype; 
		MessagingTarget prototypedMessagingTarget;

		try {

			messagingTargetPrototype = (Prototype<? extends MessagingTarget>) this.getPrototypeMessagingTarget();
			prototypedMessagingTarget = prototypingContext.instanceFor(messagingTargetPrototype);
		} catch (Xdi2MessagingException ex) {

			throw new Xdi2MessagingException("Cannot instantiate messaging target for prototype " + this.getPrototypeMessagingTarget().getClass().getSimpleName() + ": " + ex.getMessage(), ex, null);
		}

		// set the interceptor list

		if (messagingTargetPrototype instanceof AbstractMessagingTarget && prototypedMessagingTarget instanceof AbstractMessagingTarget) {

			InterceptorList interceptorListPrototype = ((AbstractMessagingTarget) messagingTargetPrototype).getInterceptors();
			InterceptorList prototypedInterceptorList = prototypingContext.instanceFor(interceptorListPrototype);

			((AbstractMessagingTarget) prototypedMessagingTarget).setInterceptors(prototypedInterceptorList);
		}

		// set the contributor map

		if (messagingTargetPrototype instanceof AbstractMessagingTarget && prototypedMessagingTarget instanceof AbstractMessagingTarget) {

			ContributorMap contributorMapPrototype = ((AbstractMessagingTarget) messagingTargetPrototype).getContributors();
			ContributorMap prototypedContributorMap = prototypingContext.instanceFor(contributorMapPrototype);

			((AbstractMessagingTarget) prototypedMessagingTarget).setContributors(prototypedContributorMap);
		}

		// mount the new messaging target

		endpointRegistry.mountMessagingTarget(messagingTargetPath, prototypedMessagingTarget);
	}

	public MessagingTarget getPrototypeMessagingTarget() {

		return this.prototypeMessagingTarget;
	}

	public void setPrototypeMessagingTarget(MessagingTarget prototypeMessagingTarget) {

		this.prototypeMessagingTarget = prototypeMessagingTarget;
	}
}
