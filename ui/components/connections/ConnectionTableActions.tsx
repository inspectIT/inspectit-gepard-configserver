import { UUID } from "crypto";
import Link from "next/link";
import { buttonVariants } from "../ui/button";

interface connectionTableActionsProps {
  id: UUID;
}
export default function connectionTableActions({
  id,
}: connectionTableActionsProps) {
  return (
    <Link
      className={buttonVariants({ variant: "outline" })}
      href={`agent-settings?connection=${id}`}
    >
      Open
    </Link>
  );
}
